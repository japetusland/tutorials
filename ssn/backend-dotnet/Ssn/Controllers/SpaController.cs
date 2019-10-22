using Ssn.Models;
using Ssn.ViewModels;
using Ssn.ViewModels.Api;
using System.Collections.Generic;
using System.Web.Mvc;

namespace Ssn.Controllers
{
    public class SpaController : Controller
    {
        private SsnSecurityService ssnSecurityService = new SsnSecurityService();

        private PostsManager postsManager = new PostsManager();

        private SpaInitialDataViewModel LoadData()
        {
            string loggedUser = User.Identity.Name;

            List<string> friends = ssnSecurityService.GetUsers();
            friends.Remove(User.Identity.Name);

            List<PostDto> postsDto = postsManager.GetPosts(loggedUser);
            List<Post> posts = postsDto.ConvertAll(post => new Post(post));
            return new SpaInitialDataViewModel(loggedUser, friends, posts);
        }

        [Authorize(Roles = Global.ROLE_USER)]
        public ActionResult React()
        {
            SpaInitialDataViewModel viewModel = LoadData();
            return View("~/Views/React/Index.cshtml", viewModel);
        }

        [Authorize(Roles = Global.ROLE_USER)]
        public ActionResult Angular()
        {
            SpaInitialDataViewModel viewModel = LoadData();
            return View("~/Views/Angular/Index.cshtml", viewModel);
        }
    }
}