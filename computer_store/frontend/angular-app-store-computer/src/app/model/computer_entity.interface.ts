// crearemos la estructura que trae la respuesta de crear usuario
import {User_Entity} from './user_entity.interface';

export interface Computer_Entity {

  id_computer: number
  processor: string
  ram_memory: string
  gpu: number
  board: string
  createdAt: string
  user: User_Entity

}
