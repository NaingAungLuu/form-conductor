// @ts-check
// Note: type annotations allow type checking and IDEs autocompletion

const lightCodeTheme = require('prism-react-renderer/themes/github');
const darkCodeTheme = require('prism-react-renderer/themes/oceanicNext');

/** @type {import('@docusaurus/types').Config} */
const config = {
  title: 'Form Conductor',
  tagline: 'A declarative form validation library for Jetpack Compose',
  url: 'https://formconductor.naingaungluu.me',
  baseUrl: '/',
  trailingSlash: false,
  onBrokenLinks: 'throw',
  onBrokenMarkdownLinks: 'warn',
  favicon: 'img/favicon.ico',

  // GitHub pages deployment config.
  // If you aren't using GitHub pages, you don't need these.
  organizationName: 'naingaungluu', // Usually your GitHub org/user name.
  projectName: 'form-conductor', // Usually your repo name.
  deploymentBranch: 'gh-pages',
  // Even if you don't use internalization, you can use this field to set useful
  // metadata like html lang. For example, if your site is Chinese, you may want
  // to replace "en" with "zh-Hans".
  i18n: {
    defaultLocale: 'en',
    locales: ['en'],
  },

  markdown: {
    mermaid: true
  },

  themes: [
    '@docusaurus/theme-mermaid'
  ],

  presets: [
    [
      'classic',
      /** @type {import('@docusaurus/preset-classic').Options} */
      ({
        docs: {
          sidebarPath: require.resolve('./sidebars.js'),
          // Please change this to your repo.
          // Remove this to remove the "edit this page" links.
          editUrl:
            'https://github.com/naingaungluu/form-conductor/tree/documentation/docs/',
        },
        blog: false,
        theme: {
          customCss: require.resolve('./src/css/custom.css'),
        },
      }),
    ],
  ],

  themeConfig:
    /** @type {import('@docusaurus/preset-classic').ThemeConfig} */
    ({
      announcementBar: {
        content: `Form conductor is now published on Maven Central as me.naingaungluu.formconductor . Check <a href="installation">Installation Docs</a> for details`,
        isCloseable: false,
        backgroundColor: "#343434",
        textColor: "#ffffff",
      },
      navbar: {
        title: 'Form Conductor',
        logo: {
          alt: 'Form Conductor Logo',
          src: 'img/logo.png',
          srcDark: 'img/logo_mono.png'
        },
        items: [
          {
            type: 'doc',
            docId: 'index',
            position: 'left',
            label: 'Docs',
          },
          {
            href: 'https://github.com/naingaungluu/form-conductor',
            label: 'GitHub',
            position: 'right',
          },
        ],
      },
      footer: {
        style: 'dark',
        links: [
          {
            title: 'Docs',
            items: [
              {
                label: 'Guides',
                to: '/docs/index',
              },
            ],
          },
          {
            title: 'Community',
            items: [
              {
                label: 'Stack Overflow',
                href: 'https://stackoverflow.com/questions/tagged/form-conductor',
              },
              {
                label: 'Twitter',
                href: 'https://twitter.com/harryluu_96',
              },
            ],
          },
          {
            title: 'More',
            items: [
              {
                label: 'GitHub',
                href: 'https://github.com/naingaungluu/form-conductor',
              },
              {
                label: 'Medium Blog',
                href: 'https://blog.naingaungluu.me',
              }
            ],
          },
        ],
        copyright: `Copyright © ${new Date().getFullYear()} naingaungluu.me. Built with Docusaurus.`,
      },
      prism: {
        theme: lightCodeTheme,
        darkTheme: darkCodeTheme,
        additionalLanguages: ['kotlin','groovy','gradle'],
      },
    }),
};

module.exports = config;
