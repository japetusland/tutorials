using Ssn.Core.Entities;
using System.Collections.Generic;

namespace Ssn.Core.Interfaces
{
    public interface IPostManager
    {
        bool AddPost(string user, string content);
        bool UpdatePost(int id, string content);
        List<Post> GetPosts(string user);
        List<Comment> GetComments(int postId);
        bool AddComment(int postId, string user, string content);
    }
}
