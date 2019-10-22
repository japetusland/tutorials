using AutoMapper;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Localization;
using Ssn.Core.Common;
using Ssn.Core.Interfaces;
using Ssn.Web.Resources;
using Ssn.Web.ViewModels.Api;
using System.Collections.Generic;

namespace Ssn.Web.Controllers.Api
{
    [Route("api/comments")]
    [ApiController]
    [Authorize(Roles = Roles.USER)]
    [ValidateAntiForgeryToken]
    public class CommentsController : ControllerBase
    {
        private readonly IPostManager postsManager;
        private readonly IMapper mapper;
        private readonly IStringLocalizer<Errors> localizer;

        public CommentsController(IStringLocalizer<Errors> localizer, IMapper mapper, IPostManager postsManager)
        {
            this.postsManager = postsManager;
            this.mapper = mapper;
            this.localizer = localizer;
        }

        [HttpPost]
        public IActionResult AddComment([FromBody]Comment comment, [FromQuery]string lang)
        {
            if (string.IsNullOrWhiteSpace(comment.Content))
                return Ok();

            if (!postsManager.AddComment(comment.PostId, User.Identity.Name, comment.Content))
            {
                var error = localizer["CommentNoAdded"];
                return StatusCode(StatusCodes.Status500InternalServerError, error);
            }

            return Ok();
        }

        [HttpGet("{id}")]
        public IActionResult GetCommentsByPost([FromRoute]int id, [FromQuery]string lang)
        {
            List<Core.Entities.Comment> comments = postsManager.GetComments(id);
            List<Comment> result = comments.ConvertAll(comment => mapper.Map<Comment>(comment));
            return Ok(result);
        }
    }
}
