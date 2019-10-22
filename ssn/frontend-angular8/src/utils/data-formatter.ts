let options = {
	year: "numeric",
	month: "numeric",
	day: "numeric",
	hour: "numeric",
	minute: "numeric",
	second: "numeric"
};

function formatTimestamp(timestamp) {
	return new Intl.DateTimeFormat(navigator.language, options).format(
		new Date(timestamp)
	);
}

export { formatTimestamp };
