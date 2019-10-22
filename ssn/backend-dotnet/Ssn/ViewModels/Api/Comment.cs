using Newtonsoft.Json;
using Ssn.Models;

namespace Ssn.ViewModels.Api
{
    public class Comment
    {
        public int Id { get; set; }

        public string User { get; set; }

        public int PostId { get; set; }

        public string Content { get; set; }

        public long Timestamp { get; set; }

        public Comment() { }

        public Comment(CommentDto comment)
        {
            Id = comment.Id;
            User = comment.User;
            PostId = comment.PostId;
            Content = comment.Content;
            Timestamp = comment.Timestamp;
        }
    }
}