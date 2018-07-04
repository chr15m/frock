;; Testing additional frock functionality

(load-file "src/alias-hacks.clj")

;; test the print alias


;; test the 'let' macro

;; test the 'cond' macro

;; test the 'when' macro

(when 1 (println "yes"))
; yes
;=>nil

(when 1 (println "yes") 2)
; yes
;=>2

(when false (println "yes") 2)
;=>nil

;; test print
(print "hi")
; hi

;; test the 'def' macro
;; test the 'fn' macro
;; test the 'defn' macro
;; test the 'partial' function
;; test type conversion functions

(php/print_r (clj->php {:hello 12}))
; Array
; (
;     [:hello] => 12
; )
;=>true

