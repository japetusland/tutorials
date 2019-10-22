using Ssn.Core.Entities;
using System.Collections.Generic;

namespace Ssn.Web.ViewModels
{
    public class SpaInitialDataViewModel
    {
        public string LoggedUser { get; set; }

        public List<string> Friends { get; set; }

        public List<Post> Posts { get; set; }
    }
}
