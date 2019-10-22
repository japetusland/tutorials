using Ssn.Controllers.Annotations;
using Ssn.Models;
using Ssn.ViewModels.Api;
using System.Collections.Generic;
using System.Globalization;
using System.Net;
using System.Net.Http;
using System.Web.Http;
namespace Ssn.Controllers.Api
{

    [ValidateXsrfToken]
    [Authorize(Roles = Global.ROLE_USER)]
    public class PostsController : ApiController
    {
        private PostsManager postsManager = new PostsManager();

        [HttpPost]
        public HttpResponseMessage AddPost(Post post, string lang)
        {
            if (string.IsNullOrWhiteSpace(post.Content))
                return Request.CreateResponse(HttpStatusCode.OK);

            if (!postsManager.AddPost(User.Identity.Name, post.Content))
            {
                var error = Resources.Errors.ResourceManager.GetString("PostNotAdded", CultureInfo.GetCultureInfo(lang));
                return Request.CreateErrorResponse(HttpStatusCode.InternalServerError, error);
            }
            return Request.CreateResponse(HttpStatusCode.OK);
        }

        [HttpPut]
        public HttpResponseMessage EditPost(Post post, string lang)
        {
            if (!postsManager.UpdatePost(post.Id, post.Content))
            {
                var error = Resources.Errors.ResourceManager.GetString("PostNotModified", CultureInfo.GetCultureInfo(lang));
                return Request.CreateErrorResponse(HttpStatusCode.InternalServerError, error);
            }
            return Request.CreateResponse(HttpStatusCode.OK);
        }

        [HttpGet]
        public HttpResponseMessage GetPostsByUser(string id, string lang)
        {
            List<PostDto> posts = postsManager.GetPosts(id);
            List<Post> result = posts.ConvertAll(post => new Post(post));
            return Request.CreateResponse(HttpStatusCode.OK, result);
        }

    }
}