(php/header "Content-type: application/json")
(php/http_response_code 200)
(println (php/json_encode {"message" "This is a test."}))
