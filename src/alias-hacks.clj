; aliases for common clojure names to mal builtins

; TODO: re-implement as actually useful macros:
; destructuring, arg checking, etc.

(defmacro! let
  (fn* (& xs)
       (if (> (count xs) 0)
         (list 'let*
               (first xs)
               (cons 'do (rest xs))))))

(defmacro! cond
  (fn* (& xs)
       (if (> (count xs) 0)
         (list 'if (first xs)
               (if (> (count xs) 1)
                 (nth xs 1)
                 (throw "odd number of forms to cond"))
               (cons 'cond (rest (rest xs)))))))

(def! print println)

; TODO: fill out these
; fn
; def
; defn
; partial

; FROCKPREAMBLEDONE