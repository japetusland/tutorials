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
    public class CommentsController : ApiController
    {
        private PostsManager postsManager = new PostsManager();

        [HttpPost]
        public HttpResponseMessage AddComment(Comment comment, string lang)
        {
            if (string.IsNullOrWhiteSpace(comment.Content))
                return Request.CreateResponse(HttpStatusCode.OK);

            if (!postsManager.AddComment(comment.PostId, User.Identity.Name, comment.Content))
            {
                var error = Resources.Errors.ResourceManager.GetString("CommentNotAdded", CultureInfo.GetCultureInfo(lang));
                return Request.CreateErrorResponse(HttpStatusCode.InternalServerError, error);
            }

            return Request.CreateResponse(HttpStatusCode.OK);
        }

        [HttpGet]
        public HttpResponseMessage GetCommentsByPost(int id, string lang)
        {
            List<CommentDto> comments = postsManager.GetComments(id);
            List<Comment> result = comments.ConvertAll(comment => new Comment(comment));
            return Request.CreateResponse(HttpStatusCode.OK, result);
        }

    }
}