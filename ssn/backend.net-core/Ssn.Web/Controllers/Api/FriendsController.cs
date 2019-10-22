using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Ssn.Core.Common;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace Ssn.Web.Controllers.Api
{
    [Route("api/friends")]
    [ApiController]
    [Authorize(Roles = Roles.USER)]
    [ValidateAntiForgeryToken]
    public class FriendsController : ControllerBase
    {
        private readonly UserManager<IdentityUser> userManager;

        public FriendsController(UserManager<IdentityUser> userManager)
        {
            this.userManager = userManager;
        }

        [HttpGet]
        public async Task<IActionResult> GetFriendsAsync()
        {
            string loggedUser = User.Identity.Name;

            var users = await userManager.GetUsersInRoleAsync(Roles.USER);
            var friends = users.Where(u => !u.UserName.Equals(loggedUser)).Select(u => u.UserName).ToList();

            return Ok(friends);
        }
    }
}
