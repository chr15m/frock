; aliases for common clojure names to mal builtins

; TODO: re-implement as actually useful macros:
; destructuring, arg checking, etc.

;*** pure aliases ***;

(def! print println)

;*** macros ***;

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

(defmacro! when
  (fn* (& xs)
       (if (> (count xs)0)
         (list 'if (first xs)
               (cons 'do (rest xs))))))

(defmacro! def
  (fn* (& xs)
       (if (> (count xs)0)
         (list 'def! (first xs)
               (cons 'do (rest xs))))))

(defmacro! fn
  (fn* (& xs)
       (if (> (count xs) 0)
         (list 'fn*
               (first xs)
               (cons 'do (rest xs))))))

(defmacro! defn
  (fn* (n & xs)
       (if (> (count xs) 0)
         (list 'def! n
               (cons 'fn xs)))))

; TODO: fill out these
; partial
; binary operators

; FROCKPREAMBLEDONE
