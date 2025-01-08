import i18n from 'i18next';
import { initReactI18next } from 'react-i18next';

i18n.use(initReactI18next).init({
  resources: {
    en: {
      translation: {
        hero: {
          title: "Howdy, I'm Robert",
          subtitle: "Fullstack Engineer",
          description:
            "I'm Robert Malloy, a curious software engineer who enjoys working across all areas of development—from embedded systems to web applications, REST APIs to tutorials. Explore my projects, and feel free to reach out if you'd like to chat!",
          contactButton: "Contact Me",
          projectsButton: "Projects",
        },
        aboutMe: {
            title: "About Me",
            card1: {
                title: "My Cats",
                content: "My fiance and I rescued two kittens that have now grown into not-so-little rowdy cats that join us in about everything we do. (Even programming lol!). They are a huge part of our little family and are just the beginning.",
            },
            card2: {
                title: "Love Hiking Outdoors",
                content: "When I'm not at my computer working on projects, I like to take time to step away from the keyboard and enjoy nature. In Florida, I don't have many opportunities for climbs, so I try to go somewhere new every year",
            },
            card3: {
                title: "Adventures in Traveling",
                content: "Traveling gives me an opportunity to experience life outside my own bubble, and allows me to talk with people from all walks of life. Seeing new places is great, but my favorite is getting to practice language skills with the locals.",
            }
        },
        contact: {
            title: "Contact",
            name: "Your Name",
            email: "Your Email",
            subject: "Your Subject",
            message: "Your Message",
            sendButton: "Send",
            sending: "Sending",
            successMessage: "'Message sent successfully!'",
            failMessage: "Failed to send the message. Please try again.",
        },
        educationModal: {
            title: "Related Courses",
            button: "Close",
            loading: "Loading courses..",
            noneFound: "No courses found.",
            error: "Failed to fetch related courses."
        },
        educationSection: {
            title: "Education",
            certs: "Certifications",
            error: "Failed to fetch data.",
            loading: "Loading data..."
        },
        experience: {
            title: "Experience",
            button: "Learn More",
            noneFound: "No experiences found.",
            error: "Failed to fetch experience data.",
            loading: "Loading experiences...",
        },
        experienceModal: {
            at: "at",
            button: "Close",
            error: "Failed to fetch responsibilities.",
            loading: "Loading responsibilities...",
        },
        footer: {
            aboutSite: "About this site: Built using React on a Java Spring API and PostgreSQL Database",
            privacyPolicy: {
                title: "Privacy Policy",
                privacyValue:   "We value your privacy and are committed to protecting your personal information. \
                                This website only stores information provided by users who:",
                use1: "Contact us via the Contact Form.",
                use2: "Use the visitor POST endpoint.",
                dataStorage:    "All data is securely stored and protected using industry-standard encryption methods. \
                                Additionally, this website uses SSL (Secure Sockets Layer) to ensure \
                                all communication between your browser and our servers is encrypted and secure.",
                sharePolicy: "We do not share or sell your information to third parties. For more details, please contact us directly.",
                button: "Close",
            },
            statusHealthy: "Status: Healthy",
            statusUnhealthy: "Status: Unhealthy",
            loading: "Checking..."
        },
        navbar: {
            menu: {
                home: "Home",
                experiences: "Experiences",
                video: "Videos",
                tutorial: "API Tutorial"
            },
            home: {
                skills: "Skills",
                experience: "Experience",
                projects: "Projects",
                aboutMe: "About Me",
                contact: "Contact"
            },
            experiences: {
                education: "Education",
                skills: "Skills",
                experience: "Experience",
                projects: "Projects",
            },
            video: {
                video: "Video",
            },
            tutorial: {
                tutorial: "API Tutorial",
                thank: "Thank You"
            }
        },
        projects: {
            title: "Projects",
            loading: "Loading projects...",
            demo: "Demo",
            repo: "Repo"
        },
        settings: {
            title: "Settings",
            language: "Language",
            darkMode: "Dark Mode",
            save: "Save Changes"
        },
        skills: {
            title: "Skills",
            backend: "Backend",
            frontend: "Frontend",
            mystery: "Mystery Box",
            button: "See More"
        },
        experienceTables: {
            education: {
                title: "Education",
                school: "Institution",
                degree: "Degree",
                duration: "Duration",
                present: "Present",
            },
            skill: {
                title: "Skills",
                name: "Name",
                type: "Specialty"
            },
            experience: {
                title: "Experience",
                company: "Company",
                role: "Role",
                duration: "Duration",
                present: "Present",
            },
            project: {
                title: "Projects",
                name: "Title",
                description: "Description"
            }
        },
        techStack: {
            title: "Tech Stack",
            button: "Close"
        },
        tutorial: {
            title: "API Testing Tutorial",
            alert: "Copied to clipboard!",
            intro: "You can test the API endpoints using cURL, Postman, or by hitting the endpoint directly in your browser.",
            getHeader: "GET Endpoint",
            getContent: "Use this cURL command to fetch my resume:",
            postHeader: "POST Endpoint",
            postContent: "Use this cURL command to send a POST request with parameters:",
            postAuthHeader: "POST Endpoint with Authorization",
            postAuthContent: "The following POST request attempts to add a visitor. If you do not feel comfortable sharing your name, leave it empty, and it will assign \"Anonymous\" to you. However, if authorization is required, the request will be rejected:",
            postAuthNote: "<strong>Note:</strong> If the endpoint requires authorization, you must include an authorization header \
            (e.g., <code>-H \"Authorization: Bearer &lt;token&gt;\"</code>). Without proper credentials, the server will \
            deny access.",
            copyButton: "Copy",
            thankYouHeader: "Thank You!",
            thankYouContent:    "A heartfelt thank you to all the visitors who have taken the time to explore my site. Your support and \
                                interest mean the world to me, and I hope you find the content here helpful and inspiring. Feel free to \
                                reach out with any feedback or questions!",
        },
        video: {
            title: "Featured Videos",
            text: "Explore video tutorials and projects I've worked on",
            loading: "Loading videos..",
            error: "Failed to load videos. Please try again later.",
        }
      },
    },
    fr: {
      translation: {
        hero: {
          title: "Salut, je suis Robert",
          subtitle: "Ingénieur Fullstack",
          description:
            "Je suis Robert Malloy, un ingénieur logiciel curieux qui aime travailler dans tous les domaines du développement : des systèmes embarqués aux applications web, des API REST aux tutoriels. Découvrez mes projets et n'hésitez pas à me contacter si vous souhaitez discuter !",
          contactButton: "Contactez-moi",
          projectsButton: "Projets",
        },
      },
    },
  },
  lng: 'en', 
  fallbackLng: 'en',
  interpolation: { escapeValue: false },
});

export default i18n;
