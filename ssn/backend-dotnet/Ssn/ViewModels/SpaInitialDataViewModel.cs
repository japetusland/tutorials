using Ssn.ViewModels.Api;
using System.Collections.Generic;
using System.IO;
using System.Web.Http;

namespace Ssn.ViewModels
{
    public class SpaInitialDataViewModel
    {

        public string LoggedUser { get; set; }

        public string Friends { get; set; }

        public string Posts { get; set; }

        public SpaInitialDataViewModel(string loggedUser, List<string> friends, List<Post> posts)
        {
            var serializer = GlobalConfiguration.Configuration.Formatters.JsonFormatter.CreateJsonSerializer();

            LoggedUser = loggedUser;

            var writer = new StringWriter();
            serializer.Serialize(writer, friends);
            Friends = writer.ToString();

            writer = new StringWriter();
            serializer.Serialize(writer, posts);
            Posts = writer.ToString();
        }

    }
}