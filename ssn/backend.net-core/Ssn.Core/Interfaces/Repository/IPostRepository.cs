using Ssn.Core.Entities;
using System.Collections.Generic;

namespace Ssn.Core.Interfaces.Repository
{
    public interface IPostRepository : IRepository<Post>
    {
        Post GetPost(int postId);
        List<Comment> GetCommentsByPost(int postId);
        List<Post> GetPostsByUser(string user);
        void InsertPost(string user, string content, long timestamp);
        void UpdatePost(int postId, string content);
        void InsertComment(Comment comment);
    }
}
