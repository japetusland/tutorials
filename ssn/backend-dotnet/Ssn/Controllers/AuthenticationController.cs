using Ssn.Models;
using Ssn.ViewModels;
using Microsoft.Owin.Security;
using System.Web;
using System.Web.Mvc;

namespace Ssn.Controllers
{
    public class AuthenticationController : Controller
    {
        private SsnSecurityService ssnSecurityService = new SsnSecurityService();

        [HttpGet]
        public ActionResult Registration()
        {
            return View();
        }

        [HttpPost]
        public ActionResult Registration(RegistrationViewModel registrationViewModel)
        {
            if (ModelState.IsValid)
            {
                var registrationResult = ssnSecurityService.CreateUser(registrationViewModel.Email, registrationViewModel.Password);
                if (registrationResult == SsnSecurityService.UserCreationResult.SUCCESS)
                    return RedirectToAction("Index", "Home");

                if (registrationResult == SsnSecurityService.UserCreationResult.USER_ALREADY_EXISTS)
                    ModelState.AddModelError("Email", Resources.Errors.UserAlreadyExists);
                else
                    ModelState.AddModelError("Email", Resources.Errors.ServerError);
            }
            return View(registrationViewModel);
        }

        public ViewResult Login(string returnUrl)
        {
            ViewBag.returnUrl = returnUrl;
            return View();
        }

        [HttpPost]
        public ActionResult Login(LoginViewModel loginViewModel, string returnUrl)
        {
            if (ModelState.IsValid)
            {
                if (ssnSecurityService.Login(loginViewModel.Email, loginViewModel.Password))
                {
                    var authenticationManager = HttpContext.GetOwinContext().Authentication;
                    authenticationManager.SignIn(new AuthenticationProperties() { IsPersistent = false }, ssnSecurityService.Identity);
                    return Redirect(returnUrl ?? "~/");
                }

                ModelState.AddModelError("Email", Resources.Errors.InvalidLogin);
            }
            return View(loginViewModel);
        }

        [Authorize]
        public ActionResult SignOut()
        {
            ssnSecurityService.Logout();
            var authenticationManager = HttpContext.GetOwinContext().Authentication;
            authenticationManager.SignOut();
            return Redirect("~/");
        }
    }
}