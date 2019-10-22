using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Ssn.Core.Common;
using Ssn.Web.Services;
using Ssn.Web.ViewModels;
using System.Threading.Tasks;

namespace Ssn.Web.Pages.React
{
    [Authorize(Roles = Roles.USER)]
    public class IndexModel : PageModel
    {
        private readonly FrontendService frontendService;

        [BindProperty]
        public SpaInitialDataViewModel InitialData { get; set; }

        public IndexModel(FrontendService frontendService)
        {
            this.frontendService = frontendService;
        }

        public async Task OnGetAsync()
        {
            InitialData = await frontendService.LoadInitialDataAsync(User.Identity.Name);
        }
    }
}
