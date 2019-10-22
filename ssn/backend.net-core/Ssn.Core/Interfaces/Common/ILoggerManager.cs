using System;

namespace Ssn.Core.Interfaces.Common
{
    public interface ILoggerManager
    {
        void Trace(string message);
        void Info(string message);
        void Warn(string message);
        void Debug(string message);
        void Error(string message);
        void Trace(Exception exception, string message = "");
        void Info(Exception exception, string message = "");
        void Warn(Exception exception, string message = "");
        void Debug(Exception exception, string message = "");
        void Error(Exception exception, string message = "");
    }
}
