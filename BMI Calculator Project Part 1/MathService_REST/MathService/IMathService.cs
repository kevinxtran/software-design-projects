using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace MathService
{ 
    [ServiceContract]
    public interface IMathService
    {
    [OperationContract]
    [WebGet]    // Add this HTTP GET attribute/directive, use default format
    double piValue();

    [OperationContract]
    [WebGet(ResponseFormat =WebMessageFormat.Json, UriTemplate = "absValue?x={x}")]             // define input format
    int absValue(int x);

    [OperationContract]
    [WebGet(UriTemplate = "/add2?x={x}&y={y}")]        // define input format
    int sum(int x, int y);

     [OperationContract]
     [WebInvoke(Method = "GET", 
     ResponseFormat = WebMessageFormat.Json, UriTemplate = "/computeStatJson?str={str}")]
     results computeStatJson(string str);

     [OperationContract]
     [WebInvoke(Method = "GET", 
     ResponseFormat = WebMessageFormat.Xml, UriTemplate = "/computeStatXML?str={str}")]
     results computeStatXML(string str);
   }

    [DataContract]
    public class results
    {
        [DataMember]
        public int digits { get; set; }
        [DataMember]
        public int upper_case_letters { get; set; }

        [DataMember]
        public int lower_case_letters { get; set; }
    }
}
