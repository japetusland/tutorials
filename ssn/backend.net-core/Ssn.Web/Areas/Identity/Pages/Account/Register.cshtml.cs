using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.Extensions.Localization;
using Microsoft.Extensions.Logging;
using Ssn.Core.Common;
using System.ComponentModel.DataAnnotations;
using System.Threading.Tasks;

namespace Ssn.Web.Areas.Identity.Pages.Account
{
    [AllowAnonymous]
    public class RegisterModel : PageModel
    {
        private readonly UserManager<IdentityUser> _userManager;
        private readonly IStringLocalizer<Resources.Errors> _localizer;
        private readonly ILogger<RegisterModel> _logger;

        public RegisterModel(
            UserManager<IdentityUser> userManager,
            SignInManager<IdentityUser> signInManager,
            IStringLocalizer<Resources.Errors> localizer,
            ILogger<RegisterModel> logger)
        {
            _userManager = userManager;
            _localizer = localizer;
            _logger = logger;
        }

        [BindProperty]
        public InputModel Input { get; set; }

        public string ReturnUrl { get; set; }

        public class InputModel
        {
            [Required(ErrorMessageResourceName = "Required", ErrorMessageResourceType = typeof(Resources.Validations))]
            [EmailAddress(ErrorMessageResourceName = "EmailAddress", ErrorMessageResourceType = typeof(Resources.Validations))]
            [Display(Name = "Email", ResourceType = typeof(Resources.Messages))]
            public string Email { get; set; }

            [Required(ErrorMessageResourceName = "Required", ErrorMessageResourceType = typeof(Resources.Validations))]
            [EmailAddress(ErrorMessageResourceName = "EmailAddress", ErrorMessageResourceType = typeof(Resources.Validations))]
            [Display(Name = "ConfirmEmail", ResourceType = typeof(Resources.Messages))]
            [Compare("Email", ErrorMessageResourceName = "CompareEmail", ErrorMessageResourceType = typeof(Resources.Validations))]
            public string ConfirmEmail { get; set; }


            [Required(ErrorMessageResourceName = "Required", ErrorMessageResourceType = typeof(Resources.Validations))]
            [StringLength(100, ErrorMessageResourceName = "StringLength", ErrorMessageResourceType = typeof(Resources.Validations), MinimumLength = 5)]
            [DataType(DataType.Password)]
            public string Password { get; set; }

            [Required(ErrorMessageResourceName = "Required", ErrorMessageResourceType = typeof(Resources.Validations))]
            [DataType(DataType.Password)]
            [Compare("Password", ErrorMessageResourceName = "ComparePasswords", ErrorMessageResourceType = typeof(Resources.Validations))]
            [Display(Name = "ConfirmPassword", ResourceType = typeof(Resources.Messages))]
            public string ConfirmPassword { get; set; }
        }

        public void OnGet(string returnUrl = null)
        {
            ReturnUrl = returnUrl;
        }

        public async Task<IActionResult> OnPostAsync(string returnUrl = null)
        {
            returnUrl = returnUrl ?? Url.Content("~/");
            if (ModelState.IsValid)
            {
                var user = new IdentityUser { UserName = Input.Email, Email = Input.Email };
                var result = await _userManager.CreateAsync(user, Input.Password);
                if (result.Succeeded)
                {
                    result = await _userManager.AddToRoleAsync(user, Roles.USER);
                    if (result.Succeeded)
                    {
                        _logger.LogInformation("User created a new account with password.");

                        return LocalRedirect(returnUrl);
                    }
                }
                foreach (var error in result.Errors)
                {
                    var localErrMsg = _localizer[error.Code];
                    if (localErrMsg != null)
                        ModelState.AddModelError(string.Empty, localErrMsg.Value);
                    else
                        ModelState.AddModelError(string.Empty, error.Description);
                }
            }

            // If we got this far, something failed, redisplay form
            return Page();
        }

    }
}
