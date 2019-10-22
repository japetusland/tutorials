using Ssn.Models;
using System.Collections.Generic;

namespace Ssn.ViewModels.Api
{
    public class Post
    {
        public int Id { get; set; }

        public string User { get; set; }

        public string Content { get; set; }

        public long Timestamp { get; set; }

        public List<Comment> Comments { get; set; }

        public Post() { }

        public Post(PostDto post)
        {
            Id = post.Id;
            User = post.User;
            Content = post.Content;
            Timestamp = post.Timestamp;
            Comments = post.Comments.ConvertAll(comment => new Comment(comment));
        }
    }
}