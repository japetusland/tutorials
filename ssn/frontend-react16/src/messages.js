const MESSAGES = {
    "en": {
        "writeNewPost" : "Write a new post",
        "writeNewComment" : "Write a new comment",
        "add" : "Add",
        "save": "Save",
        "post": "Post",
        "comment": "Comment",
        "friends": "Friends",
        "myDashboard": "My Dashboard"
    },
    "it": {
        "writeNewPost" : "Scrivi un nuovo post",
        "writeNewComment" : "Scrivi un nuovo commento",
        "add" : "Aggiungi",
        "save": "Salva",
        "post": "Post",
        "comment": "Commento",
        "friends": "Amici",
        "myDashboard": "Bacheca"
    }
}

export let message = key => {
    try
    {
        let lang = navigator.language;
        if (lang)
            lang = lang.substring(0, 2).toLowerCase();
        else 
            lang = "en";
        return MESSAGES[lang][key];    
    } catch(err) {
        console.log("cannot find key:" + key);
        console.log(err);
        return "";
    }
}