using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Web;
using System.Net;
using System.IO;
using System.Xml;
using System.Net.Http;
using System.Runtime.Serialization.Json;
using Newtonsoft.Json;


namespace MathClient
{
    class Program
    {
        static void Main(string[] args)
        {
          
            stat();

            Console.ReadKey();

           

            
        }

        public static void stat()
        {
            string name = "HelloWorld";

            string url = @"http://localhost:55666/MathService.svc/computeStatJson?str=" + name;

            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(url);
            WebResponse response = request.GetResponse();
            Stream responseStream = response.GetResponseStream();

            StreamReader reader = new StreamReader(responseStream);
            String json = reader.ReadToEnd();
            Console.WriteLine(json);
            processJsonNewtonSoft(json);

        }


        static void processJsonNewtonSoft(String data)
        {
            results p = JsonConvert.DeserializeObject<results>(data);
            Console.WriteLine(p.lower_case_letters);

        }


        [Serializable]

        public class results
        {
        
            public int digits { get; set; }
           
            public int upper_case_letters { get; set; }

            public int lower_case_letters { get; set; }
        }
        
    }

}
