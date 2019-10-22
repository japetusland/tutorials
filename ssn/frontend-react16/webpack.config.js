let path = require("path");

module.exports = {
  entry: ["@babel/polyfill", "./src/index.jsx"],
  mode: "production",
  output: {
    filename: "main.js",
    path: path.resolve(__dirname, "dist"),
    publicPath: ""
  },
  devServer: {
    contentBase: path.join(__dirname, "dist"),
    compress: true
  },
  module: {
    rules: [
      {
        test: /\.(js|jsx)$/,
        exclude: /node_modules/,
        use: {
          loader: "babel-loader"
        }
      }
    ]
  },
  performance: { hints: false }
};
