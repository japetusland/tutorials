using Ssn.Core.Interfaces.Repository;
using System.Collections.Generic;

namespace Ssn.Core.Entities
{
    public class Post: IAggregatorRoot
    {
        public int Id { get; set; }
        public string User { get; set; }
        public string Content { get; set; }
        public long Timestamp { get; set; }

        public List<Comment> Comments { get; set; }

        public Post()
        {
            Comments = new List<Comment>();
        }
    }
}
