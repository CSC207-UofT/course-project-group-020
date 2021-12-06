import React, { Suspense, Fragment, useState, useRef } from 'react';
import { MathUtils } from 'three';
import { PerspectiveCamera, MeshDistortMaterial } from '@react-three/drei';
import { useFrame } from '@react-three/fiber';
import { useSpring } from '@react-spring/core';
import { a } from '@react-spring/three';

const hoverColor = '#fff';
const notHoverColor = '#444';

const AnimatedMaterial = a(MeshDistortMaterial);

export default function Scene() {
  const sphere = useRef(null);
  const [hovered, setHovered] = useState(false);
  const [down, setDown] = useState(false);

  // Animate with React-Spring
  const [{ wobble, color, ambient }] = useSpring(
    {
      wobble: down ? 1.1 : hovered ? 1.05 : 1,
      color: hovered ? hoverColor : notHoverColor,
      config: n => n === 'wobble' && hovered && { mass: 5, friction: 8, tension: 500 }
    }, [hovered, down]);

  // Bubble floats and follows mouse on hover
  useFrame((state) => {
    const sphereRef = sphere.current;
    if (sphereRef) {
      sphereRef.position.x = MathUtils.lerp(sphereRef.position.x, hovered ? state.mouse.x : 0, 0.2);
      sphereRef.position.y = MathUtils.lerp(sphereRef.position.y, hovered ? state.mouse.y : 0, 0.2);
    }
  });

  return (
    <Fragment>
      <PerspectiveCamera>
        <a.ambientLight intensity={ambient} />
      </PerspectiveCamera>
      <Suspense fallback={null}>
        <a.mesh
          ref={sphere}
          scale={wobble}
          onPointerOver={() => setHovered(true)}
          onPointerOut={() => setHovered(false)}
          onPointerDown={() => setDown(true)}
          onPointerUp={() => setDown(false)}>
          <sphereBufferGeometry args={[1.4, 64, 64]} />
          <AnimatedMaterial color={color} metalness={0.4} />
        </a.mesh>
      </Suspense>
    </Fragment>
  );
}
