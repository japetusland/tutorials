class Post {
    id: number;
    user: string;
    content: string;
    timestamp: number;
    comments: Comment[];
}

class Comment {
    id: number;
    postId: number;
    user: string;
    content: string;
    timestamp: number;
}

export { Post, Comment };


  