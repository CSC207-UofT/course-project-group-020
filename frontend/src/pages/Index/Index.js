import { Canvas } from '@react-three/fiber';

import Scene from './Scene';
import Overlay from './Overlay';

const Index = () => {
  return (
    <div className='page page-index'>
      <Canvas>
        <Scene />
      </Canvas>
      <Overlay />
    </div>
  );
};

export default Index;
