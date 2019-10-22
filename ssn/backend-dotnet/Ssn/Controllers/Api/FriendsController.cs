using Ssn;
using Ssn.Controllers.Annotations;
using Ssn.Models;
using System.Collections.Generic;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace Sssn.Controllers.Api
{

    [ValidateXsrfToken]
    [Authorize(Roles = Global.ROLE_USER)]
    public class FriendsController : ApiController
    {
        private SsnSecurityService ssnSecurityService = new SsnSecurityService();

        [HttpGet]
        public HttpResponseMessage GetFriends()
        {
            IList<string> users = ssnSecurityService.GetUsers();
            users.Remove(User.Identity.Name);
            return Request.CreateResponse(HttpStatusCode.OK, users);
        }

    }
}