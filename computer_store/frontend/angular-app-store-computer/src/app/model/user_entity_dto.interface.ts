// crearemos la estructura que trae la respuesta de crear usuario
import {Computer_Entity} from './computer_entity.interface';

export interface User_Entity_Dto {


  name: string
  lastname: string
  identificationDocument: number
  numberPhone: number
  email: string
  password: string

  computers: Computer_Entity[]

}
