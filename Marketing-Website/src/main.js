import "./style.css";

import * as THREE from "three";

import { OrbitControls } from "three/examples/jsm/controls/OrbitControls";

// Create a scene
const scene = new THREE.Scene();

// Create a camera
const camera = new THREE.PerspectiveCamera( 75, window.innerWidth / window.innerHeight, 0.1, 1000 );

// Create a renderer
const renderer = new THREE.WebGLRenderer({
  canvas: document.querySelector("#bg"), });

renderer.setPixelRatio( window.devicePixelRatio );
renderer.setSize( window.innerWidth, window.innerHeight );
camera.position.setZ(30);

renderer.render( scene, camera );

const textureLoader = new THREE.TextureLoader();
const torusTexture = textureLoader.load('blackhole.jpeg');
const bumpMap = textureLoader.load('moon.jpeg'); // Add a bump map for more depth

const geometry = new THREE.TorusGeometry(10, 3, 16, 100);
const material = new THREE.MeshStandardMaterial({
  map: torusTexture,
  bumpMap: bumpMap, // Apply the bump map
  bumpScale: 0.5, // Adjust the bump scale
  metalness: 0.4, // Increase metalness
  roughness: 0.2 // Decrease roughness
});
const torus = new THREE.Mesh(geometry, material);

scene.add(torus);

// Add more lights to the scene
const pointLight = new THREE.PointLight(0xffffff);
pointLight.position.set(5, 5, 5);

const ambientLight = new THREE.AmbientLight(0xffffff);
const directionalLight = new THREE.DirectionalLight(0xffffff, 0.5);
directionalLight.position.set(-5, 5, 5);

scene.add(pointLight, ambientLight, directionalLight);

const lightHelper = new THREE.PointLightHelper(pointLight);
const gridHelper = new THREE.GridHelper(200, 50);

function createBlueDot(text) {
  const canvas = document.createElement('canvas');
  canvas.width = 512; // Increase canvas width
  canvas.height = 256; // Increase canvas height
  const context = canvas.getContext('2d');
  context.font = 'Bold 65px Impact';
  context.fillStyle = '#C38823';
  context.fillText(text, 20, 130); // Adjust text positioning

  const texture = new THREE.CanvasTexture(canvas);
  texture.image.textContent = text; // Store the text content in the texture image
  const spriteMaterial = new THREE.SpriteMaterial({ map: texture });
  const sprite = new THREE.Sprite(spriteMaterial);
  sprite.scale.set(20, 10, 1); // Increase the scale of the sprite

  return sprite;
}

const numDots = 5;
const radius = 30; // Define the radius for the circular pattern
const dotNames = ["Scientific Fields", "Business Fields", "Mathematic Fields", "Engineering Fields", "Technology Fields"];

// Create a geometry and material for the dots
const dotGeometry = new THREE.SphereGeometry(1.5, 32, 32); // Adjust the size and detail as needed
const dotMaterial = new THREE.MeshStandardMaterial({ color: 0x2B25C0 }); // Blue color
const outlineMaterial = new THREE.MeshBasicMaterial({ color: 0xffffff, side: THREE.BackSide }); // White color for outline

const dots = []; // Array to store the dot meshes
const outlines = []; // Array to store the outline meshes

for (let i = 0; i < numDots; i++) {
  const angle = (i / numDots) * 2 * Math.PI; // Calculate the angle for each dot
  const x = radius * Math.cos(angle);
  const y = radius * Math.sin(angle);
  const z = 0; // Keep the z position constant for a flat circular pattern

  // Create a mesh for each dot
  const dotMesh = new THREE.Mesh(dotGeometry, dotMaterial);
  dotMesh.position.set(x, y, z);

  // Create an outline mesh for each dot
  const outlineMesh = new THREE.Mesh(dotGeometry, outlineMaterial);
  outlineMesh.scale.multiplyScalar(1.1); // Slightly larger than the dot
  outlineMesh.visible = false; // Hide the outline initially
  dotMesh.add(outlineMesh); // Add the outline as a child of the dot mesh

  // Create a blue dot sprite with text
  const sprite = createBlueDot(dotNames[i]);
  sprite.position.set(0, 2, 0); // Position the text above the dot
  dotMesh.add(sprite); // Add the sprite as a child of the dot mesh

  scene.add(dotMesh); // Add the dot mesh to the scene
  dots.push(dotMesh); // Store the dot mesh in the array
  outlines.push(outlineMesh); // Store the outline mesh in the array
}

const raycaster = new THREE.Raycaster();
const mouse = new THREE.Vector2();

// Create a HTML element to display the text
const infoBox = document.createElement('div');
infoBox.style.position = 'absolute';
infoBox.style.top = '50%';
infoBox.style.left = '50%';
infoBox.style.transform = 'translate(-50%, -50%)'; // Center the info box
infoBox.style.padding = '20px';
infoBox.style.backgroundColor = 'rgba(0, 0, 0, 0.7)';
infoBox.style.color = 'white';
infoBox.style.borderRadius = '10px';
infoBox.style.boxShadow = '0 0 10px rgba(0, 0, 0, 0.5)';
infoBox.style.maxWidth = '400px';
infoBox.style.textAlign = 'left';
infoBox.style.display = 'none'; // Hide the info box initially
infoBox.style.zIndex = '1000'; // Ensure the info box is on top
document.body.appendChild(infoBox);



function onMouseMove(event) {
  // Calculate mouse position in normalized device coordinates
  mouse.x = (event.clientX / window.innerWidth) * 2 - 1;
  mouse.y = -(event.clientY / window.innerHeight) * 2 + 1;

  // Update the raycaster with the camera and mouse position
  raycaster.setFromCamera(mouse, camera);

  // Calculate objects intersecting the ray
  const intersects = raycaster.intersectObjects(dots);

  // Hide all outlines
  outlines.forEach(outline => outline.visible = false);

  // Show the outline for the intersected dots
  for (let i = 0; i < intersects.length; i++) {
    intersects[i].object.children.forEach(child => {
      if (child instanceof THREE.Mesh && child.material === outlineMaterial) {
        child.visible = true;
      }
    });
  }
}

const dotTexts = [
  "<h2>📐 EpicCalculator</h2><p>In the world of science, accuracy is paramount.<br>Whether you're analyzing data, testing hypotheses, or developing models,<br>Calculi gives you the tools you need to get results you can trust.</p><ul><li>Handle Complex Equations with Ease</li><li>Save Time for Experimentation</li><li>Ensure Results!</li></ul>",
  "<h2>📈 EpicCalculator</h2><p>In business, time is money, and accuracy is everything.<br>Calculi helps you make data-driven decisions with fast, reliable calculations<br>for everything from financial modeling to forecasting.</p>",
  "<h2>🧠 Elevate Your Mathematical Work with Precision and Efficiency</h2><p>From pure math to applied fields, Calculi is the perfect tool for anyone in mathematics.<br>With a focus on precision and advanced functionality,<br>it's tailored for solving complex equations, analyzing functions, and more.</p>",
  "<h2>👷‍♂️ Designed for the Complexities of Engineering Calculations</h2><p>Engineers face some of the most challenging and precise calculations.<br>Whether you're designing, testing, or analyzing,<br>Calculi helps you perform these calculations swiftly and accurately,<br>ensuring the integrity of your projects.</p>",
  "<h2>💻 Speed and Precision for Coding, Development, and Algorithms</h2><p>In tech, accuracy and speed are essential for developing reliable software,<br>optimizing algorithms, and solving complex problems.<br>Calculi is designed to meet the demands of modern tech professionals.</p>"
];

function onClick(event) {
  // Calculate mouse position in normalized device coordinates
  mouse.x = (event.clientX / window.innerWidth) * 2 - 1;
  mouse.y = -(event.clientY / window.innerHeight) * 2 + 1;

  // Update the raycaster with the camera and mouse position
  raycaster.setFromCamera(mouse, camera);

  // Calculate objects intersecting the ray
  const intersects = raycaster.intersectObjects(dots, true);

  if (intersects.length > 0) {
    const intersectedObject = intersects[0].object;

    // Check if the intersected object is a dot mesh
    if (intersectedObject.parent && intersectedObject.parent instanceof THREE.Mesh) {
      const intersectedDot = intersectedObject.parent;

      // Zoom in on the clicked dot
      const targetPosition = new THREE.Vector3();
      intersectedDot.getWorldPosition(targetPosition);
      gsap.to(camera.position, {
        duration: 1,
        x: targetPosition.x,
        y: targetPosition.y,
        z: targetPosition.z + 10, // Adjust the zoom distance as needed
        onUpdate: () => {
          camera.lookAt(targetPosition);
        }
      });

      // Display the text in the info box
      const dotIndex = dots.indexOf(intersectedDot);
      const text = dotTexts[dotIndex]; // Get the corresponding text from the array
      infoBox.innerHTML = text; // Display the text
      infoBox.style.display = 'block'; // Show the info box
    }
  } else {
    infoBox.style.display = 'none'; // Hide the info box if no dot is clicked
  }
}

window.addEventListener('mousemove', onMouseMove, false);
window.addEventListener('click', onClick, false);
scene.add(lightHelper, gridHelper);

const controls = new OrbitControls(camera, renderer.domElement);

function addStar() {
  const geometry = new THREE.SphereGeometry(0.25, 24, 24);
  const material = new THREE.MeshStandardMaterial({ color: 0xffffff });
  const star = new THREE.Mesh(geometry, material);

  const [x, y, z] = Array(3).fill().map(() => THREE.MathUtils.randFloatSpread(100));

  star.position.set(x, y, z);
  scene.add(star);
}

// Avatar
const calcTexture = new THREE.TextureLoader().load("calc.jpg");

const calc = new THREE.Mesh(
  new THREE.BoxGeometry(6, 6, 6),
  new THREE.MeshBasicMaterial({ map: calcTexture })
);

scene.add(calc);



scene.add(pointLight, ambientLight);
scene.add(torus);
scene.add(lightHelper);
scene.add(gridHelper);

Array(300).fill().forEach(addStar);

const spaceTexture = new THREE.TextureLoader().load("interstellar.avif");

// Set the scene's background to the loaded texture
scene.background = spaceTexture;



function animate() {
  requestAnimationFrame( animate );

  torus.rotation.x += 0.001;
  torus.rotation.y += 0.0001;
  torus.rotation.z += 0.001;

  controls.update();

  renderer.render( scene, camera );
}

animate();

// Handle window resize
window.addEventListener('resize', () => {
  const aspectRatio = 16 / 9;
  let newWidth = window.innerWidth;
  let newHeight = window.innerHeight;

  if (newWidth / newHeight > aspectRatio) {
    newWidth = newHeight * aspectRatio;
  } else {
    newHeight = newWidth / aspectRatio;
  }

  camera.aspect = aspectRatio;
  camera.updateProjectionMatrix();
  renderer.setSize(newWidth, newHeight);
});