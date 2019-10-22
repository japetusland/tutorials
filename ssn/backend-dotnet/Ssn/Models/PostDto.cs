using Ssn.DAL;
using System.Collections.Generic;

namespace Ssn.Models
{
    public class PostDto
    {
        public int Id { get; set; }

        public string User { get; set; }

        public string Content { get; set; }

        public long Timestamp { get; set; }

        public List<CommentDto> Comments { get; set; }

        public PostDto(Post post)
        {
            Id = post.Id;
            User = post.User;
            Content = post.Content;
            Timestamp = post.Timestamp;
            Comments = post.Comments.ConvertAll(comment => new CommentDto(post.Id, comment));
        }

    }
}