(php/header "Content-type: application/json")
(php/http_response_code 200)

(let [x (get php/_GET "x")
      filename "file-append.txt"]
  (if x
    (php/file_put_contents filename (str x "\n") php/FILE_APPEND))
  (println (php/json_encode {"content" (php/file_get_contents filename)})))
