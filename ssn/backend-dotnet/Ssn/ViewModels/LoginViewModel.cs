using System;
using System.ComponentModel.DataAnnotations;

namespace Ssn.ViewModels
{
    public class LoginViewModel
    {
        [Required(ErrorMessageResourceName = "Required", ErrorMessageResourceType = typeof(Resources.Validations))]
        [EmailAddress(ErrorMessageResourceName = "EmailAddress", ErrorMessageResourceType = typeof(Resources.Validations))]
        [Display(Name = "Email", ResourceType = typeof(Resources.Messages))]
        public String Email { get; set; }

        [Required(ErrorMessageResourceName = "Required", ErrorMessageResourceType = typeof(Resources.Validations))]
        [DataType(DataType.Password)]
        public String Password { get; set; }
    }
}