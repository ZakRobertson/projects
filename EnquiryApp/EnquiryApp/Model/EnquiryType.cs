using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema; 

namespace EnquiryApp.Model
{
    public class EnquiryType
    {
        [Key,DatabaseGenerated(DatabaseGeneratedOption.Identity)] 
        public int typeId { get; set; }
        public string typeName { get; set; } = string.Empty;
        // copies columns from EnquiryType table in database
    }
}
