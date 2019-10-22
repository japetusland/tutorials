let initialDataFromBackend = document.getElementById("initialDataFromBackend");

export let getPageData = (key, def) => {
	let result = "";
	try
	{
		result = initialDataFromBackend.getAttribute(key);
		if (result == null) {
			console.log("cannot find key: " + key);
			return def;
		}
	}
	catch
	{
		console.log("cannot find key: " + key);
		return def;
	}
	try
	{
		return JSON.parse(result);
	}
	catch
	{
		return result;
	}
}

export const LOGGED_USER = getPageData("data-logged-user", "<no-user>");
export const CONTEXT_ROOT = getPageData("data-context-root", "");

export let clone = x => {
	if (Array.isArray(x)) 
	return x.map(i=>clone(i));
	if (typeof(x)=="object") {
	var y = {};
	for (var k in x)
		y[k] = clone(x[k]);
	return y;
	}
	return x;
}