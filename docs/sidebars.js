/**
 * Creating a sidebar enables you to:
 - create an ordered group of docs
 - render a sidebar for each doc of that group
 - provide next/previous navigation

 The sidebars can be generated from the filesystem, or explicitly defined here.

 Create as many sidebars as you want.
 */

// @ts-check

/** @type {import('@docusaurus/plugin-content-docs').SidebarsConfig} */
const sidebars = {
  docs: [
    "setup",
    {
      type: 'category',
      label: 'Core',
      link: {
        type: "doc",
        id: "core/index"
      },
      collapsed: false,
      items: [
        'core/basic',
        'core/qualifier',
        'core/binds',
        'core/extras',
        'core/component',
      ],
    },
    "test",
    {
      type: 'category',
      label: 'Android',
      link: {
        type: "doc",
        id: "android/index"
      },
      collapsed: true,
      items: [
        'android/application',
        'android/viewmodel',
        'android/activity',
        'android/fragment',
        'android/components',
        'android/tests',
      ],
    },
    {
      type: 'category',
      label: 'Compose',
      link: {
        type: "doc",
        id: "compose/index"
      },
      collapsed: true,
      items: [
        'compose/core',
        'compose/viewmodel',
      ],
    },
  ],
};

module.exports = sidebars;
