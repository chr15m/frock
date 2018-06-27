; scrape top new articles from hn

(defn fetch-article [id]
  (php/json_decode
    (php/file_get_contents
      (str "https://hacker-news.firebaseio.com/v0/item/" id ".json"))))

(defn fetch-top-articles []
  (let [ids (php/json_decode
              (php/file_get_contents
                "https://hacker-news.firebaseio.com/v0/topstories.json"))
        ids (php/array_slice ids 0 10)]
    (php/array_map
      (fn [id] (fetch-article id))
      ids)))

(defn main []
  (php/header "Content-type: text/plain")
  (let [articles (fetch-top-articles)
        articles (php/array_map
                   (fn [article]
                     (let [article (php->clj article)]
                       (str (get article "title") "\n"
                            (get article "url") "\n")))
                   articles)]
    (print (php/join "\n" articles))))

(main)
