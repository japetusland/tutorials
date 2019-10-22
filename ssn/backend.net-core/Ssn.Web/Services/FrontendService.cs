using Microsoft.AspNetCore.Identity;
using Ssn.Core.Common;
using Ssn.Core.Entities;
using Ssn.Core.Interfaces;
using Ssn.Web.ViewModels;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Ssn.Web.Services
{
    public class FrontendService
    {
        private readonly IPostManager postManager;
        private readonly UserManager<IdentityUser> userManager;

        public FrontendService(UserManager<IdentityUser> userManager, IPostManager postManager)
        {
            this.postManager = postManager;
            this.userManager = userManager;
        }

        public async Task<SpaInitialDataViewModel> LoadInitialDataAsync(string loggedUser)
        {
            IList<IdentityUser> users = await userManager.GetUsersInRoleAsync(Roles.USER);
            List<string> friends = users.Where(u => !u.UserName.Equals(loggedUser))
                                        .Select(u => u.UserName)
                                        .ToList();

            List<Post> posts = postManager.GetPosts(loggedUser);

            return new SpaInitialDataViewModel
            {
                LoggedUser = loggedUser,
                Friends = friends,
                Posts = posts
            };
        }
    }
}
