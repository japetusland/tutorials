using AutoMapper;
using Ssn.Infrastructure.Data;

namespace Ssn.Infrastructure.Converters
{
    public class AutoMapperProfile: Profile
    {

        public AutoMapperProfile()
        {
            CreateMap<Core.Entities.Comment, Comment> ();
            CreateMap<Comment, Core.Entities.Comment>();
            CreateMap<Post, Core.Entities.Post>();
        }
    }
}
