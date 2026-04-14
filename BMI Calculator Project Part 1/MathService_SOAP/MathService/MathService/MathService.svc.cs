using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;
namespace MathService
{
    public class MathService : IMathService
    {
        public double piValue()
        {
            return Math.PI;
        }
        public int absValue(int x)
        {
            if (x >= 0)
                return x;
            else
                return(-x);
        }
        public int add2(int x, int y)
        {
            int sum = 0;
            sum = x + y;
            return sum;
        }
        public results computeStat(string str)
        {
            results res = new results();
            int d=0, u=0, l = 0;
            for (int i = 0; i < str.Length; i++)
            {
                if (Char.IsUpper(str[i]))
                {
                    u = u + 1;
                }
                if (Char.IsLower(str[i]))
                {
                    l = l + 1;
                }
                if (Char.IsDigit(str[i]))
                {
                    d = d + 1;
                }
            }
            res.digits = d;
            res.upper_case_letters = u;
            res.lower_case_letters = l;

            return res;
        }
     }
}
