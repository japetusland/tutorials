using Ssn.DAL;

namespace Ssn.Models
{
    public class CommentDto
    {
        public int Id { get; set; }

        public string User { get; set; }

        public int PostId { get; set; }

        public string Content { get; set; }

        public long Timestamp { get; set; }

        public CommentDto(int postId, Comment comment)
        {
            Id = comment.Id;
            User = comment.User;
            PostId = postId;
            Content = comment.Content;
            Timestamp = comment.Timestamp;
        }
    }
}