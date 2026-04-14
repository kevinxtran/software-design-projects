using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SOAPMathClient
{
    class Program
    {
        static void Main(string[] args)
        {
            mathRef.MathServiceClient mc = new mathRef.MathServiceClient();
            // call the piValue Method
            Console.WriteLine(" PI Value = " + mc.piValue());

            //Call Absolute Value Method
            int number = -10;
            int result = mc.absValue(number);
            Console.WriteLine(" Absolute Value = " + result);

            // Call computeStat Method
            mathRef.results res = mc.computeStat("hello world");
            Console.WriteLine("Lowercase count= " + res.lower_case_letters.ToString());
        }
    }
}
