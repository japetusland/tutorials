using NLog;
using Ssn.Core.Interfaces.Common;
using System;

namespace Ssn.Infrastructure.Logger
{
    public class LoggerManager: ILoggerManager
    {
        private static ILogger logger = LogManager.GetCurrentClassLogger();

        public void Trace(string message)
        {
            logger.Trace(message);
        }

        public void Debug(string message)
        {
            logger.Debug(message);
        }

        public void Error(string message)
        {
            logger.Error(message);
        }

        public void Info(string message)
        {
            logger.Info(message);
        }

        public void Warn(string message)
        {
            logger.Warn(message);
        }

        public void Trace(Exception exception, string message = "")
        {
            logger.Trace(exception, message);
        }

        public void Info(Exception exception, string message = "")
        {
            logger.Info(exception, message);
        }

        public void Warn(Exception exception, string message = "")
        {
            logger.Warn(exception, message);
        }

        public void Debug(Exception exception, string message = "")
        {
            logger.Debug(exception, message);
        }

        public void Error(Exception exception, string message = "")
        {
            logger.Error(exception, message);
        }
    }
}
