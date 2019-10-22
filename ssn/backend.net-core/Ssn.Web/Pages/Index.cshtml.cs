using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Ssn.Core.Common;

namespace Ssn.Web.Pages
{
    [Authorize(Roles = Roles.USER)]
    public class IndexModel : PageModel
    {
        public void OnGet()
        {
        }
    }
}
