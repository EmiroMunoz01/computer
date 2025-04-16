// crearemos la estructura que trae la respuesta de crear usuario
import {Computer} from './computer.interface';

export interface User {

  id: number
  name: string
  lastname: string
  identification_document: number
  email: string
  password: string
  computers: Computer
  createdAt: string

}
