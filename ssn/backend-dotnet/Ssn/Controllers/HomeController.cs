using System.Web.Mvc;

namespace Ssn.Controllers
{
    public class HomeController : Controller
    {
        [Authorize(Roles = Global.ROLE_USER)]
        public ActionResult Index()
        {
            return View();
        }
    }
}