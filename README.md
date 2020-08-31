# jv-stream-api

1. Given a List of Integer numbers,
   return a sum of odd numbers or 0, if there are no odd numbers in the List.  
   
2. Given a List of Strings, 
   return a number of times the `element` String occurs in the List. 
    
3. Given a List of Strings, return the Optional of its first element.  

4. Given a List of Strings, 
   find the String equal to the passed `element` or throw NoSuchElementException.
     
5. Given a List of Integer numbers,
   subtract 1 from each element on an odd position (having the odd index).  
   Then return the average of all odd numbers or throw NoSuchElementException. 
    
6. Given a List of `People` instances (having `name`, `age` and `sex` fields),  
   for example, `Arrays.asList( new People(«Вася», 16, Sex.MAN), new People(«Елена», 42, Sex.WOMEN))`,  
   select from the List only men whose age is from `fromAge` to `toAge` inclusively.  
   
   _Example: select men who can be recruited to army (from 18 to 27 years old inclusively)._
   
7. Given a List of `People` instances (having `name`, `age` and `sex` fields),  
   for example, `Arrays.asList( new People(«Вася», 16, Sex.MAN), new People(«Елена», 42, Sex.WOMEN))`,  
   select from the List only people whose age is from `fromAge` and to `maleToAge` (for men) or to `femaleToAge` (for women) inclusively.  
   
   _Example: select people of working age (from 18 y.o. and to 60 y.o. for men and to 55 y.o. for women inclusively)._
   
7. Given a List of `People` instances (having `name`, `age`, `sex` and `List<Cat> cats` fields, 
   and each `Cat` having a `name` and `age`),  
   return the names of all cats whose owners are women from `femaleAge` years old inclusively.  
