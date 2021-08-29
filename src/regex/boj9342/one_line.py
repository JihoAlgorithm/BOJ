import re
exec("print('Infected!'if re.match('^[A-F]?A+F+C+[A-F]?$',input())else'Good');"*int(input()))