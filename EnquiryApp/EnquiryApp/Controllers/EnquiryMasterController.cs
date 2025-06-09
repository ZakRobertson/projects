using Azure.Identity;
using EnquiryApp.Model;
using Microsoft.AspNetCore.Cors;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace EnquiryApp.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    [EnableCors("AllowCors")]
    public class EnquiryMasterController : ControllerBase
    {
        private readonly EnquiryDBContext _context;
        public EnquiryMasterController(EnquiryDBContext context)
        {
            _context = context;
        }
        //injecting the database context into the controller

        [HttpGet("GetAllStatus")]
        public List<EnquiryStatus> GetEnquiryStatus()
        {
            //returns all the enquiry statuses from the database
            return _context.EnquiryStatus.ToList();
        }

        [HttpGet("GetAllTypes")]
        public List<EnquiryType> GetEnquiryTypes()
        {
            //returns all the enquiry types from the database
            return _context.EnquiryType.ToList();
        }

        [HttpGet("GetAllEnquiries")]
        public List<Enquiry> GetAllEnquiries()
        {
            //returns all the enquiries from the database
            return _context.EnquiryModel.ToList();
        }
        //route and method names can be different

        [HttpPost("CreateNewEnquiry")] // <-- route name
        public Enquiry CreateNewEnquiry([FromBody] Enquiry enquiry) //CreateNewEnquiry is method name in this context
        {
            //creates a new enquiry in the database
            enquiry.createdDate = DateTime.Now; //sets the created date to the current date and time
            _context.EnquiryModel.Add(enquiry);
            _context.SaveChanges(); //saves the changes to the database
            return enquiry;
        }

        [HttpPut("UpdateEnquiry")]
        public Enquiry UpdateEnquiry([FromBody] Enquiry enquiry)
        {
            //updates an existing enquiry in the database
            var existingEnquiry = _context.EnquiryModel.Find(enquiry.enquiryId);
            if (existingEnquiry != null)
            {
                existingEnquiry.resolution = enquiry.resolution;
                existingEnquiry.enquiryStatusId = enquiry.enquiryStatusId;
                _context.SaveChanges();
            }
            return existingEnquiry;
        }

        [HttpDelete("DeleteEnquiry")]
        public bool DeleteEnquiry(int id)
        {
            //deletes an enquiry from the database
            var enquiry = _context.EnquiryModel.Find(id);
            if (enquiry == null)
            {
                return false;
            }
            _context.EnquiryModel.Remove(enquiry);
            _context.SaveChanges();
            return true; 
        }
    }

}
