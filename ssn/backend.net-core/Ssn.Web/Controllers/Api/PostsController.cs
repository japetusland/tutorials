using AutoMapper;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Ssn.Core.Common;
using Ssn.Core.Interfaces;
using Ssn.Web.ViewModels.Api;
using System.Collections.Generic;

namespace Ssn.Web.Controllers.Api
{
    [Route("api/posts")]
    [ApiController]
    [Authorize(Roles = Roles.USER)]
    [ValidateAntiForgeryToken]
    public class PostsController : ControllerBase
    {
        private readonly IPostManager postManager;
        private readonly IMapper mapper;

        public PostsController(IPostManager postManager, IMapper mapper)
        {
            this.postManager = postManager;
            this.mapper = mapper;
        }

        [HttpPost]
        public IActionResult AddPost([FromBody]Post post, [FromQuery]string lang)
        {
            if (string.IsNullOrWhiteSpace(post.Content))
                return Ok();

            if (!postManager.AddPost(User.Identity.Name, post.Content))
            {
                return StatusCode(StatusCodes.Status500InternalServerError, Resources.Errors.PostNotAdded);
            }
            return Ok();
        }

        [HttpPut]
        public IActionResult EditPost([FromBody]Post post, [FromQuery]string lang)
        {
            if (!postManager.UpdatePost(post.Id, post.Content))
            {
                return StatusCode(StatusCodes.Status500InternalServerError, Resources.Errors.PostNotModified);
            }
            return Ok();
        }

        [HttpGet("{id}")]
        public IActionResult GetPostsByUser([FromRoute]string id, [FromQuery]string lang)
        {
            List<Core.Entities.Post> posts = postManager.GetPosts(id);
            List<Post> result = posts.ConvertAll(post => mapper.Map<Post>(post));
            return Ok(result);
        }
    }
}