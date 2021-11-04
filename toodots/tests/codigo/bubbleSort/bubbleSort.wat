(module
(type $_sig_i32ri32 (func (param i32) (result i32)))
(type $_sig_i32 (func (param i32)))
(type $_sig_ri32 (func (result i32)))
(type $_sig_void (func ))
(import "runtime" "exceptionHandler" (func $exception (type $_sig_i32)))
(import "runtime" "print" (func $print (type $_sig_i32)))
(import "runtime" "read" (func $read (type $_sig_ri32)))
(memory 2000)
(global $SP (mut i32) (i32.const 0)) ;; start of stack
(global $MP (mut i32) (i32.const 0)) ;; mark pointer
(global $NP (mut i32) (i32.const 131071996)) ;; heap 2000*64*1024-4
(start $process)
(func $process (type $_sig_void)
   (local $localsStart i32)
   i32.const 232
   call $reserveStack  ;; returns old MP (dynamic link)
   get_global $MP
   i32.store
   get_global $MP
   get_global $SP
   i32.store offset=4
   get_global $MP
   i32.const 8
   i32.add
   set_local $localsStart
   i32.const 0
   i32.const 0
   i32.add
   i32.const 10
   i32.store
   i32.const 4
   i32.const 0
   i32.add
   i32.const 64
   i32.store
   i32.const 8
   i32.const 0
   i32.add
   i32.const 34
   i32.store
   i32.const 12
   i32.const 0
   i32.add
   i32.const 25
   i32.store
   i32.const 16
   i32.const 0
   i32.add
   i32.const 12
   i32.store
   i32.const 20
   i32.const 0
   i32.add
   i32.const 22
   i32.store
   i32.const 24
   i32.const 0
   i32.add
   i32.const 11
   i32.store
   i32.const 28
   i32.const 0
   i32.add
   i32.const 90
   i32.store
   i32.const 32
   i32.const 0
   i32.add
   i32.const 3
   i32.store
   i32.const 36
   i32.const 0
   i32.add
   i32.const 15
   i32.store
   i32.const 40
   i32.const 0
   i32.add
   i32.const 99
   i32.store
   i32.const 44
   i32.const 0
   i32.add
   i32.const 0
   i32.store
   block
   loop
   i32.const 44
   i32.const 0
   i32.add
   i32.load
   i32.const 0
   i32.const 0
   i32.add
   i32.load
   i32.const 1
   i32.sub
   i32.lt_s
   i32.eqz
   br_if 1
   i32.const 48
   i32.const 0
   i32.add
   i32.const 0
   i32.store
   block
   loop
   i32.const 48
   i32.const 0
   i32.add
   i32.load
   i32.const 0
   i32.const 0
   i32.add
   i32.load
   i32.const 44
   i32.const 0
   i32.add
   i32.load
   i32.sub
   i32.const 1
   i32.sub
   i32.lt_s
   i32.eqz
   br_if 1
   i32.const 4
   i32.const 0
   i32.add
   i32.const 4
   i32.const 48
   i32.const 0
   i32.add
   i32.load
   i32.mul
   i32.add
   i32.load
   i32.const 4
   i32.const 0
   i32.add
   i32.const 4
   i32.const 48
   i32.const 0
   i32.add
   i32.load
   i32.const 1
   i32.add
   i32.mul
   i32.add
   i32.load
   i32.gt_s
   if
   i32.const 52
   i32.const 0
   i32.add
   i32.const 4
   i32.const 0
   i32.add
   i32.const 4
   i32.const 48
   i32.const 0
   i32.add
   i32.load
   i32.mul
   i32.add
   i32.load
   i32.store
   i32.const 4
   i32.const 0
   i32.add
   i32.const 4
   i32.const 48
   i32.const 0
   i32.add
   i32.load
   i32.mul
   i32.add
   i32.const 4
   i32.const 0
   i32.add
   i32.const 4
   i32.const 48
   i32.const 0
   i32.add
   i32.load
   i32.const 1
   i32.add
   i32.mul
   i32.add
   i32.load
   i32.store
   i32.const 4
   i32.const 0
   i32.add
   i32.const 4
   i32.const 48
   i32.const 0
   i32.add
   i32.load
   i32.const 1
   i32.add
   i32.mul
   i32.add
   i32.const 52
   i32.const 0
   i32.add
   i32.load
   i32.store
   end
   i32.const 48
   i32.const 0
   i32.add
   i32.const 48
   i32.const 0
   i32.add
   i32.load
   i32.const 1
   i32.add
   i32.store
   br 0
   end
   end
   i32.const 44
   i32.const 0
   i32.add
   i32.const 44
   i32.const 0
   i32.add
   i32.load
   i32.const 1
   i32.add
   i32.store
   br 0
   end
   end
   i32.const 48
   i32.const 0
   i32.add
   i32.const 0
   i32.store
   block
   loop
   i32.const 48
   i32.const 0
   i32.add
   i32.load
   i32.const 0
   i32.const 0
   i32.add
   i32.load
   i32.lt_s
   i32.eqz
   br_if 1
   i32.const 4
   i32.const 0
   i32.add
   i32.const 4
   i32.const 48
   i32.const 0
   i32.add
   i32.load
   i32.mul
   i32.add
   i32.load
   call $print
   i32.const 48
   i32.const 0
   i32.add
   i32.const 48
   i32.const 0
   i32.add
   i32.load
   i32.const 1
   i32.add
   i32.store
   br 0
   end
   end
   call $freeStack
)
(func $reserveStack (param $size i32)
   (result i32)
   get_global $MP
   get_global $SP
   set_global $MP
   get_global $SP
   get_local $size
   i32.add
   set_global $SP
   get_global $SP
   get_global $NP
   i32.gt_u
   if
   i32.const 3
   call $exception
   end
)
(func $freeStack (type $_sig_void)
   get_global $MP
   i32.load
   i32.load offset=4
   set_global $SP
   get_global $MP
   i32.load
   set_global $MP   
)
)