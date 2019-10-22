using System;
using System.ComponentModel.DataAnnotations;

namespace Ssn.ViewModels
{
    public class RegistrationViewModel
    {
        [Required(ErrorMessageResourceName = "Required", ErrorMessageResourceType = typeof(Resources.Validations))]
        [EmailAddress(ErrorMessageResourceName = "EmailAddress", ErrorMessageResourceType = typeof(Resources.Validations))]
        [Display(Name = "Email", ResourceType = typeof(Resources.Messages))]
        public String Email { get; set; }

        [Required(ErrorMessageResourceName = "Required", ErrorMessageResourceType = typeof(Resources.Validations))]
        [EmailAddress(ErrorMessageResourceName = "EmailAddress", ErrorMessageResourceType = typeof(Resources.Validations))]
        [Display(Name = "ConfirmEmail", ResourceType = typeof(Resources.Messages))]
        [Compare("Email", ErrorMessageResourceName = "CompareEmail", ErrorMessageResourceType = typeof(Resources.Validations))]
        public String ConfirmEmail { get; set; }


        [Required(ErrorMessageResourceName = "Required", ErrorMessageResourceType = typeof(Resources.Validations))]
        [StringLength(100, ErrorMessageResourceName = "StringLength", ErrorMessageResourceType = typeof(Resources.Validations), MinimumLength = 5)]
        [DataType(DataType.Password)]
        public String Password { get; set; }

        [Required(ErrorMessageResourceName = "Required", ErrorMessageResourceType = typeof(Resources.Validations))]
        [DataType(DataType.Password)]
        [Compare("Password", ErrorMessageResourceName = "ComparePasswords", ErrorMessageResourceType = typeof(Resources.Validations))]
        [Display(Name = "ConfirmPassword", ResourceType = typeof(Resources.Messages))]
        public String ConfirmPassword { get; set; }
    }
}