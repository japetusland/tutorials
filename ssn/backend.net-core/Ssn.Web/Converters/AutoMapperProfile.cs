using AutoMapper;
using Ssn.Web.ViewModels.Api;

namespace Ssn.Web.Converters
{
    public class AutoMapperProfile : Profile
    {
        public AutoMapperProfile()
        {
            CreateMap<Core.Entities.Comment, Comment>();
            CreateMap<Core.Entities.Post, Post>();
        }
    }
}
